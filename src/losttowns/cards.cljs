(ns losttowns.cards
  (:require [rum.core :as rum]
            [losttowns.leaflet :as leaflet]
            [losttowns.core :as losttowns])
  (:require-macros [devcards.core :refer [defcard]]))

(defcard losttowns-toolbar
  (losttowns/add-toolbar))

(defcard leaflet-card
  (leaflet/simple-map {:pos [51.505 -0.09] :zoom 13})
  {}
  {:padding false})

(defonce hellostate (atom {:text "Hello"}))

(rum/defc hello < rum/reactive []
  (let [text (:text (rum/react hellostate))]
    [:div {:on-click (fn [_] (swap! hellostate update-in [:text] #(case % 
                                                                    "Hello" "Goodbye"
                                                                    "Goodbye" "Hello")))} 
     [:p text]]))

(rum/defc hellos []
  (js/React.createElement "div" nil (array (hello) (hello))))

(defcard hello-card
  (hellos))

(defcard twin-leaflet-card
  (leaflet/twin-map {:pos [51.505 -0.09] :zoom 13}) {} {:padding false})


