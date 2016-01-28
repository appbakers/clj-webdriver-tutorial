(ns clj-webdriver-tutorial.handler
  (:require
            [clj-webdriver-tutorial.views :as views]
            [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            ))

(defroutes app-routes
  (GET "/"
       []
       (views/home-page))
  (GET "/add-location"
       []
       (views/add-location-page))
  (POST "/add-location"
        {params :params}
        (views/add-location-results-page params))
  (GET "/location/:loc-id"
       [loc-id]
       (views/location-page loc-id))
  (GET "/all-locations"
       []
       (views/all-locations-page))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
