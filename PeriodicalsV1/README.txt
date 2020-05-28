Start app with command "npm start" in the project root folder.
The proxy will start.
The properties were changed are the line in "package.json" 
FROM
"start": "ng serve", 
TO
"start": "ng serve --proxy-config src\\proxy.conf.json",

Also proxy configuration file was created.
The proxy needed to enable CORS.
Eventually, errors were fixed by starting proxy.

Sources:
https://www.youtube.com/watch?v=OjmZPPKaj6A
https://enable-cors.org/server_expressjs.html on backend

Interesting:
https://www.youtube.com/watch?v=NzEEIiDytBI