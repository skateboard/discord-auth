<h1 align="center">Discord Auth</h1>
<div align="center">
  <strong>A basic licensing system with Discord OAuth.</strong>
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
  },
  "notifications": {
    "enabled": false,
    "webhook_url": "WEBHOOK_URL",
    "mode": "basic"
  }
}
```

# Installation
Clone the repo
```
git clone https://github.com/skateboard/discord-auth.git
```
Setup in a ide like Eclipse or Intelij. Setup the database with ```discordauth.sql``` tables. Once all that is done run the program once and it will create a config edit the config properties.
