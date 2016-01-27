(ns clj-webdriver-tutorial.features.config)

(def test-port 5744)
(def test-host "localhost")
(def test-base-url (str "http://" test-host ":" test-port "/"))
