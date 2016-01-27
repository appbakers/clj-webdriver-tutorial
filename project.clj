(defproject clj-webdriver-tutorial "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler clj-webdriver-tutorial.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]
			[clj-webdriver "0.7.1"]
			[org.seleniumhq.selenium/selenium-server "2.46.0"]
			[ring/ring-jetty-adapter "1.4.0"]]}})
