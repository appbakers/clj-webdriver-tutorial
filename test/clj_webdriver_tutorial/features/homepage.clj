(ns clj-webdriver-tutorial.features.homepage
  (:require [clojure.test :refer :all]
  	[ring.adapter.jetty :refer [run-jetty]]
	[clj-webdriver.taxi :refer :all]
	[clj-webdriver-tutorial.features.config :refer :all]
	[clj-webdriver-tutorial.handler :refer [app-routes]]))

;; Fixtures

(defn start-server []
	(loop [server (run-jetty app-routes {:port test-port, :join? false})]
	(if (.isStarted server)
		server
		(recur server))))
(defn stop-server [server]
	(.stop server))
(defn start-browser []
	(set-driver! {:browser :chrome}))
(defn stop-browser []
	(quit))
(defn with-server [t]
  (let [server (start-server)]
    (t)
    (stop-server server)))
(defn with-browser [t]
  (start-browser)
  (t)
  (stop-browser))

(use-fixtures :once with-server with-browser)

;; Tests

(deftest homepage-greeting
	(to test-base-url)
	(is (= (text "body") "Hello World")))
