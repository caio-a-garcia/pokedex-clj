(ns dev.query
  (:require
   [honeysql.core :as sfmt]
   [honeysql.helpers :as smft-help]
   [next.jdbc :as jdbc]
   [next.jdbc.sql :as sql]
   [app.state :refer [db]]
   [next.jdbc.result-set :as rs]
   [app.state-config :refer [config]]))

;; (def db-config (:db @config))

(def sqlmap
  {:select [:pokemon_name]
   :from [:pokemon]
   :where [:= :pokedex_number 102]})

(def ds (jdbc/get-datasource db-config))

(comment
  (jdbc/execute! ds [(str sqlmap)])


  (sql/query @db (sfmt/format sqlmap)
             {:return-keys true :builder-fn rs/as-unqualified-lower-maps}))
