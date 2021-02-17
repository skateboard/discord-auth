<h1 align="center">Discord Auth</h1>
<div align="center">
  <strong>A basic example of Discord OAuth using Spring</strong>
</div>
<br />

# Images
<img src="https://cdn.discordapp.com/attachments/705971421570138142/811388106049585192/unknown.png" >

<img src="https://cdn.discordapp.com/attachments/705971421570138142/811388907266244638/unknown.png" >

# Config Example
```json
{
  "name": "Test Panel",
  "port": 8080,
  "oauth":{
    "client_id": "3123123123123123123",
    "client_secret": "dasdasdasdasdasdasdasd",
    "redirect": "http://127.0.0.1:8080/auth/callback?",
    "scopes":[
      "identify",
      "email"
    ]
  },
  "mysql":{
    "ip": "localhost",
    "port": 3306,
    "username": "root",
    "password": "",
    "database": "discordauth"
  }
}
```