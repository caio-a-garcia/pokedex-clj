(ns app.state-config
  (:require
   [mount.lite :as mount]))

(defn- start
  []
  (->
   (slurp "config.edn")
   (read-string)))

(defn- stop
  []
  {:db nil})

(mount/defstate config
  :start (start)
  :stop (stop))
