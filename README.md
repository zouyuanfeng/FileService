### 配置文件
src/main/resources/application.yml
```yaml
server:
  port: 8886 #启动的端口号
spring:
  servlet:
    multipart:
      max-file-size: 10MB
      max-request-size: 10MB
app:
  image-dir: temp_img #上传后文件保存的地方"${System.getProperty("user.home")}/${appProps?.imageDir}/"
  image-url: http://60.205.180.227/pic/ #图片的访问路径
```

### nginx配置
/etc/nginx/nginx.conf
```
user root;
worker_processes auto;
error_log /var/log/nginx/error.log;
pid /run/nginx.pid;

# Load dynamic modules. See /usr/share/nginx/README.dynamic.
include /usr/share/nginx/modules/*.conf;

events {
    worker_connections 1024;
}

http {
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile            on;
    tcp_nopush          on;
    tcp_nodelay         on;
    keepalive_timeout   65;
    types_hash_max_size 2048;

    include             /etc/nginx/mime.types;
    default_type        application/octet-stream;

    # Load modular configuration files from the /etc/nginx/conf.d directory.
    # See http://nginx.org/en/docs/ngx_core_module.html#include
    # for more information.
    include /etc/nginx/conf.d/*.conf;

    server {
        listen       80 default_server;
        listen       [::]:80 default_server;
        server_name  _;
        #root         /home/lili/html;

        # Load configuration files for the default server block.
        include /etc/nginx/default.d/*.conf;
        location / {
        }

        location /fileService {
            proxy_pass http://127.0.0.1:8886/;
        }
        location /pic {
             alias   /home/zou/temp_img/;
             index  index.html index.htm;
         }

        error_page 404 /404.html;
            location = /40x.html {
        }

        error_page 500 502 503 504 /50x.html;
            location = /50x.html {
        }
    }


```