(ns losttowns.cards
  (:require [rum.core :as rum]
            [rum.mdl :as mdl]
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


(rum/defc hello < rum/reactive [text]
  (let [current-text (:text (rum/react hellostate))]
    [:div {:on-click (fn [_] (swap! hellostate update-in [:text] reverse))}
     [:p current-text]]))

(rum/defc hellos [text]
  (js/React.createElement "div" nil (array 
                                      (rum/with-key (hello text) "1")
                                      (rum/with-key (hello text) "2"))))

(defonce map-state (atom {:pos [51.505 -0.09] :zoom 13}))

(rum/defc slider-map < rum/reactive []
  (let [{:keys [pos zoom] :as state} (rum/react map-state)]
    [:div nil
     (mdl/slider {:value (str (* 100 (float (/ zoom 19)))), :tab-index "0", :on-change (fn [e]
                                                                                        (let [percent (.. e -target -value)
                                                                                              zoom (int (* (float (/ percent 100)) 19))]
                                                                                          (swap! map-state assoc :zoom zoom)))})
     (leaflet/twin-map state)]))

(defcard hello-card
  (hellos "Go away"))

(defcard twin-leaflet-card
  (slider-map) {} 
  {:padding false :inspect-data true :history true})


