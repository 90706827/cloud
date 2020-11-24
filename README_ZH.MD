#
## NGINX 
```shell script
    server {
        listen 80; # 图片服务器
        server_name  www.images.zgc.com;
		location / {
            root D:/temp/;
			autoindex on; 
         }
    }
    server {
        listen       80;
        server_name  www.zgc.com;
		location / {
			proxy_headers_hash_max_size 51200;
			proxy_headers_hash_bucket_size 6400;
			proxy_set_header Host $host;
			proxy_set_header X-Real-IP $remote_addr;
			proxy_set_header REMOTE-HOST $remote_addr;
			proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
			add_header Access-Control-Allow-Origin *;
		    add_header Access-Control-Allow-Headers X-Requested-With;
		    add_header Access-Control-Allow-Methods GET,POST,PUT,DELETE,OPTIONS;
		   if ($request_method = 'OPTIONS') {
           				return 204;
           }
           if ($request_method != 'OPTIONS'){
           		proxy_pass http://127.0.0.1:8080;
           }
		}
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
```
## HOST
```shell script
127.0.0.1 www.zgc.com
127.0.0.1 www.server.zgc.com
127.0.0.1 www.images.zgc.com
```

## nginx.bat
```bat
@ECHO OFF
D:\nginx-1.18.0\nginx.exe
ping 127.0.0.1 -n 1>NUL
echo Starting nginx
echo .
echo .
echo .
ping 127.0.0.1 >NUL
EXIT
```