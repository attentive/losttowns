(ns losttowns.core
  (:require [rum.core :as rum]
            cljsjs.react-leaflet)) ;; js/ReactLeaflet

(enable-console-print!)

;; defonce means this doesn't get redefined on page reload
(defonce app-state (atom {:text "I see!"}))

(rum/defc hello < rum/reactive []
  (let [text (:text (rum/react app-state))]
    [:div [:p text]]))

(defn build
  ([component props]
   (build component props (array)))
  ([component props & children]
   (.createElement js/React
                   component
                   (clj->js props)
                   (array children))))

(def tile-layer (partial build js/ReactLeaflet.TileLayer))
(def leaflet-map (partial build js/ReactLeaflet.Map))
(def marker (partial build js/ReactLeaflet.Marker))
(def popup (partial build js/ReactLeaflet.Popup))

(rum/mount (hello)
           (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
