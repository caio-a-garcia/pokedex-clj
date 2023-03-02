(ns back.query
  (:require
   [back.state :refer [db]]
   [next.jdbc :as jdbc]
   [next.jdbc.sql :as sql]
   [next.jdbc.result-set :as rs]
   [honeysql.core :as sfmt]
   [honeysql.helpers :as sfmt-help]))


(defn get-info
  [pokedex_num]
  (->
   {:select [:*]
    :from [:Pokemon]
    :where [:= :pokedex_number pokedex_num]}
   (sfmt/format)
   (#(sql/query @db % {:builder-fn rs/as-unqualified-lower-maps}))))

(defn get-info-by-pokedex-num
  []
  (fn [_ pokedex_num _]
    (get-info pokedex_num)))


(defn impl [arg]
  arg)

(defn check-input
  []
  (fn [_ args _]
    (impl args)))

