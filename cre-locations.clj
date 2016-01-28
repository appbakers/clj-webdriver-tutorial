(require '[clojure.java.jdbc :as sql])

(def db-spec
  '{:classname "org.h2.Driver"
   :subprotocol "h2:file"
   :subname "db/clj-webdriver-tutorial"})

(defn clob-to-string [row]
  (assoc row :text (with-open [rdr (java.io.BufferedReader. (.getCharacterStream (:text row)))]
    (apply str (line-seq rdr)))))

(sql/with-connection
  db-spec
  (sql/create-table :locations
                    [:id "bigint primary key auto_increment"]
                    [:x "integer"]
                    [:y "integer"])
  (sql/insert-records :locations
                      {:x 8 :y 9}))

