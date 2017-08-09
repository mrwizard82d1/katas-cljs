(ns supermarket-pricing.core
    (:require [om.core :as om :include-macros true]
              [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(println "This text is printed from src/supermarket-pricing/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:bean-pricing 
                          {:header "Bean Pricing"
                           :unit-price-label "Price (per can):"
                           :unit-label "Can(s):"
                           :extended-price-label "Extended price:"}}))


(om/root
  (fn [data owner]
    (reify om/IRender
      (render [_]
        (html [:div 
               [:h1 (get-in data [:bean-pricing :header])]
               [:div [:span 
                      [:label {:for :unit-price} (get-in data [:bean-pricing :unit-price-label])]
                      [:input {:type :number
                               :id :unit-price}]]]
               [:div [:span 
                      [:label {:for :unit} (get-in data [:bean-pricing :unit-label])]
                      [:input {:type :number
                               :id :unit}]]]
               [:div [:span 
                      [:label {:for :extended-price} (get-in data [:bean-pricing :extended-price-label])]
                      [:input {:type :number
                               :id :extended-price}]]]
               ])
        )))
  app-state
  {:target (. js/document (getElementById "app"))})

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
