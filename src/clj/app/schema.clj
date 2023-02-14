(ns app.schema
  (:require
   [clojure.edn :as edn]
   [com.walmartlabs.lacinia.schema :as schema]
   [com.walmartlabs.lacinia.util :as util]
   [app.query :as query]))

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
