(ns losttowns.leaflet
  (:require-macros [losttowns.macros :as m])
  (:require [rum.core :as rum]
            cljsjs.react-leaflet)) ;; js/ReactLeaflet

(m/adapt-react leaflet-map js/ReactLeaflet.Map)
(m/adapt-react tile-layer js/ReactLeaflet.TileLayer)
(m/adapt-react marker js/ReactLeaflet.Marker)
(m/adapt-react popup js/ReactLeaflet.Popup)

(defn osm-tiles []
  (tile-layer {:url "http://{s}.tile.osm.org/{z}/{x}/{y}.png"
               :attribution "&copy; <a href=\"http://osm.org/copyright\">OpenStreetMap</a> contributors"}))

(rum/defc simple-map [{:keys [pos zoom] :as state} & children] 
  (apply (partial leaflet-map {:center pos :zoom zoom} (osm-tiles)) children))

(rum/defc twin-map [{:keys [pos zoom] :as state}] 
  [:div.side-by-side nil (simple-map state) (simple-map state)])

