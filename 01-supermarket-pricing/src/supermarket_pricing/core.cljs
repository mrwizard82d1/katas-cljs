(ns supermarket-pricing.core
    (:require [om.core :as om :include-macros true]
              [sablono.core :as html :refer-macros [html]]))

(enable-console-print!)

(println "This text is printed from src/supermarket-pricing/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:bean-pricing 
                          {:model {:unit-price 0.65
                                   :quantity 3
                                   :extended-price (* 0.65 3)}
                           :view-model 
                           {:header "Bean Pricing"
                            :unit-price-label "Price (per can):"
                            :unit-label "Can(s):"
                            :extended-price-label "Extended price:"}}}))

(defn when-bean-unit-price-changed [evt owner state]
  (let [unit-price (.. evt -target -value)]
    (om/set-state! owner :unit-price unit-price)
    (om/set-state! owner :extended-price (* unit-price (:quantity state)))))

(defn when-bean-quantity-changed [evt owner state]
  (let [quantity (.. evt -target -value)]
    (om/set-state! owner :quantity (.. evt -target -value))
    (om/set-state! owner :extended-price (* (:unit-price state) quantity))))

(om/root
  (fn [data owner]
    (reify 
      om/IInitState 
      (init-state [_]
        (get-in data [:bean-pricing :model])) 
      om/IRenderState
      (render-state [this state]
        (html [:div 
               [:h1 (get-in data [:bean-pricing :view-model :header])]
               [:div [:span 
                      [:label {:for :unit-price} (get-in data [:bean-pricing :view-model :unit-price-label])]
                      [:input {:type :number
                               :id :unit-price
                               :value (:unit-price state)
                               :on-change #(when-bean-unit-price-changed % owner state)}]]]
               [:div [:span 
                      [:label {:for :unit} (get-in data [:bean-pricing :view-model :unit-label])]
                      [:input {:type :number
                               :id :unit
                               :value (:quantity state)
                               :on-change #(when-bean-quantity-changed % owner state)}]]]
               [:div [:span 
                      [:label {:for :extended-price} (get-in data [:bean-pricing :view-model :extended-price-label])]
                      [:input {:id :extended-price
                               :read-only true
                               :value (.toLocaleString (:extended-price state) 
                                                       "us-US" 
                                                       #js {:style "currency" :currency "USD"})}]]]
               ])
        )))
  app-state
  {:target (. js/document (getElementById "app"))})

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

