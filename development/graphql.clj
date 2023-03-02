(ns dev.graphql
  "For manualy testing the graphql endpoints of the application."
  (:require
   [app.schema :as s]
   [clojure.data.json :as json]
   [com.walmartlabs.lacinia :as lacinia]))

(def schema (s/load-structure))

(def sqlmap
  {:select [:pokemon_name]
   :from [:pokemon]
   :where [:= :pokedex_number 102]})

(defn q
  [query-string]
  (lacinia/execute schema query-string nil nil))


(comment
  "Query getInfoByPokedexNumber esta retornando um erro sem indicacao clara
   de onde o erro esta ocorrendo. A mensagem passa uma Position: 47.
   essa posicao nao mudou apos editar: app.query")
