(ns losttowns.cards
  (:require [losttowns.core :as losttowns])
  (:require-macros [devcards.core :refer [defcard]]))

(enable-console-print!)

(defcard hello
  (losttowns/hello))

#_(defcard map-card
  (losttowns/leaflet))

(defcard leaflet
  (fn [state]
    (let [{:keys [pos zoom] :as st} @state
          tiles (losttowns/tile-layer {:url "http://{s}.tile.osm.org/{z}/{x}/{y}.png"
                          :attribution "&copy; <a href=\"http://osm.org/copyright\">OpenStreetMap</a> contributors"})
          mk (losttowns/marker {:position pos})]
      (losttowns/leaflet-map {:center pos :zoom zoom} tiles mk)))
  {:pos [51.505, -0.09]
   :zoom 13}
  {:header true})

