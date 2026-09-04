
# WildFly Social App

Prototype. Factory-generated Social app from wildfly-builder.

Catalog: https://sw-builder.com/appstore/wildfly/apps/wildfly-social-app.html

Builder: https://github.com/Gator-Go/wildfly-builder

Live demo: https://sw-builder.com/social/do?op=Home  
Sign in with `guest` / `guest`.

## Build (Unix)

Prerequisites: Git, Groovy, JDK, Maven, WildFly.

Expected sibling directories:

    ~/wildfly/wildfly-builder
    ~/wildfly/wildfly-social-app

```bash
cd ~/wildfly/wildfly-social-app
git pull
./wildfly-social-build-deploy.sh
```
## Layout:
```text
wildfly-social-app/
├── wildfly-social-build-deploy.sh
├── Extender/
│   ├── SocialExtender.groovy
│   └── PostingCommentList.jsp
├── options/
│   ├── APP_CODE_TYPES.xml
│   ├── APP_ENUMS.xml
│   ├── APP_EVENTS.xml
│   ├── APP_HOME.xml
│   ├── APP_NAMES.xml
│   └── APP_TABLES.xml
└── social_logo.png
```
## Note:

template/ and build/ appear after a build. They come from wildfly-builder.

WildFlyBuilder.groovy, updateAppsList.groovy, and update_and_commit.sh
are copied in from wildfly-builder at build time.

SocialExtender.groovy performs functions unique to the social app.
PostingCommentList.jsp is a social-app extension page.

The social/ dir appears after a build and is the build output where
the new app is created.