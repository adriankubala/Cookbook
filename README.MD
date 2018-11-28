# Cookbook API

## Endpoints
- GET /dishes
- GET /dishes?term={term} - e.g.:
```
/dishes?term=%20%20CraM%20%20%20brEA%20
```
- GET /dishes/{dishId}
- GET /ingredients
- POST /ingredients - e.g.:
```
{
    "name": "butter"
}
```
- POST /dishes - e.g.:
```
{
    "name": "Chocolate cake - yummy",
    "picture": "data:image/jpeg;base64,/9j/4AAQSkZJ",
    "recipe": "Mix together the cake mix, 1 cup amaretto flavored creamer, 1 cup amaretto liqueur, eggs and oil until blended. Distribute cake batter evenly between the cake pans. Bake in the preheated oven for 15 minutes, making certain the cake layers do not overbake. Allow to cool completely before filling.",
    "ingredientIds": [
        1,
        51
    ]
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
