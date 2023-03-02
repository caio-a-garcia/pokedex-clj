(ns front.core
  (:require [reagent.dom]
            [re-frame.core :as rf :refer [dispatch dispatch-sync]]))

(defn app
  []
  ;; View's main entry point
  [:h1 "Um comeco."])

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
  (render))

