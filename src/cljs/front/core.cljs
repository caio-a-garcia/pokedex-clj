(ns front.core
  (:require [reagent.dom]
            [re-frame.core :as rf :refer [dispatch dispatch-sync]]
            ["victory" :as v]))

(def sample-data
  [{:quarter 1 :earnings 13000}
   {:quarter 2 :earnings 16500}
   {:quarter 3 :earnings 14250}
   {:quarter 4 :earnings 19000}])

(defn graph
  []
  [v.victory-bar {:data sample-data
                 :x "quarter"
                 :y "earnings"}])

(defn header
  []
  [:header
   [:p "Pokedex"]])

(defn card
  []
  [:div {:class "card m-4"}
   [:div {:class "flex card-container red-container"}
    [:p "card"]]]
  )

(defn app
  []
  ;; View's main entry point
  [:div
   [header]
   [:div {:class "flex justify-content-center"}
    [card]
    [graph]
    [card]]])

(defn render
  []
  (reagent.dom/render [app]
                      (.getElementById js/document "app")))

(defn ^:dev/after-load clear-cache-and-render!
  []
  ;; The `:dev/after-load` metadata causes this function to be called
  ;; after shadow-cljs hot-reloads code. We force a UI update by clearing
  ;; the Reframe subscription cache.
  (rf/clear-subscription-cache!)
  (render))

(defn ^:export main
  []
  (rf/clear-subscription-cache!)
  (render))

