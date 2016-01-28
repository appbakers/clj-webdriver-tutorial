(ns clj-webdriver-tutorial.views
  (:require [clj-webdriver-tutorial.db :as db]
            [clojure.string :as str]
            [hiccup.page :as h]))

(defn gen-page-head
  [title]
  [:head
   [:title (str "Locations: " title)]
   (h/include-css "/css/styles.css")])

(def header-links
  [:div#header-links
   "[ "
   [:a {:href "/"} "Home"]
   " | "
   [:a {:href "/add-location"} "Add a Location"]
   " | "
   [:a {:href "/all-locations"} "View All Locations"]
   " ]"])

(defn home-page
  []
  (h/html5
    (gen-page-head "Home")
    header-links
    [:h1 "Home"]
    [:p "Webapp to store and display some 2D (x,y) locations."]))

(defn add-location-page
  []
  (h/html5
    (gen-page-head "Add a Location")
    header-links
    [:h1 "Add a Location"]
    [:form {:action "/add-location" :method "POST"}
     [:p "x value: " [:input {:type "text" :name "x"}]]
     [:p "y value: " [:input {:type "text" :name "y"}]]
     [:p [:input {:type "submit" :value "submit location"}]]]))

(defn add-location-results-page
  [{:keys [x y]}]
  (let [id (db/add-location-to-db x y)]
    (h/html5
      (gen-page-head "Added a Location")
      header-links
      [:h1 "Added a Location"]
      [:p "Added [" x ", " y "] (id: " id ") to the db. "
       [:a {:href (str "/location/" id)} "See for yourself"]
       "."])))

(defn location-page
  [loc-id]
  (let [{x :x y :y} (db/get-xy loc-id)]
    (h/html5
      (gen-page-head (str "Location " loc-id))
      header-links
      [:h1 "A Single Location"]
      [:p "id: " loc-id]
      [:p "x: " x]
      [:p "y: " y])))

(defn all-locations-page
  []
  (let [all-locs (db/get-all-locations)]
    (h/html5
      (gen-page-head "All locations in the db")
      header-links
      [:h1 "All Locations"]
      [:table
       [:tr [:th "id"] [:th "x"] [:th "y"]]
       (for [loc all-locs]
         [:tr [:td (:id loc)] [:td (:x loc)] [:td (:y loc)]])])))


