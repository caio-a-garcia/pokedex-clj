(ns app.state
  "Starts and stops database connection based on one or more 
  configurations."
  (:require
   [mount.lite :as mount]
   [app.state-config :refer [config]]
   [next.jdbc.connection :as connection])
  (:import
   [com.zaxxer.hikari HikariDataSource]))

(defn- start
  []
  (connection/->pool HikariDataSource (:db @config)))

(defn- stop
  [state]
  (.close @state))

(mount/defstate db
  :start (start)
  :stop (stop db))
