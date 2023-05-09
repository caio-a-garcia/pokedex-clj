(ns back.schema
  (:require
   [clojure.edn :as edn]
   [com.walmartlabs.lacinia.pedestal2 :as p2]
   [com.walmartlabs.lacinia.schema :as schema]
   [com.walmartlabs.lacinia.util :as util]
   [io.pedestal.http :as http]
   [back.query :as query]))

(defn resolver-map
  []
  {:query/getInfoByPokedexNumber (query/get-info-by-pokedex-num)
   :query/returnInputs (query/check-input)})

(defn load-structure
  []
  (-> (slurp "graphql.edn")
      edn/read-string
      (util/attach-resolvers (resolver-map))
      schema/compile))

(def service (-> (load-structure)
                 (p2/default-service nil)
                 http/create-server
                 http/start))
