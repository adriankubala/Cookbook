# Cookbook API

## Endpoints
- GET /dishes
- GET /dishes/{dishId}
- GET /ingredients
- POST /ingredients - e.g.:
```
{
    "name":"butter"
}
```

## Deployment to Heroku
1. Clone repository.
2. Open terminal in the root directory and log-in with your credentials:
```
heroku login
```
3. Create Heroku app:
```
heroku create example-app
```
4. Push repo to Heroku:
```
git subtree push --prefix cookbook/ heroku master
```
5. Set appropriate Spring Profiles:
```
heroku config:set SPRING_PROFILES_ACTIVE=dev,pgsql
```
If you don't want test data to be inserted into DB just skip `dev` profile.

6. Open the app:
```
heroku open
```
App will be available at https://example-app.herokuapp.com
