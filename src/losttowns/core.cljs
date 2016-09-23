(ns losttowns.core
  (:require [rum.core :as rum]
            [rum.mdl :as mdl]
            [losttowns.react-utils :as react]
            [losttowns.leaflet :as leaflet]
            cljsjs.react-leaflet)) ;; js/ReactLeaflet

(enable-console-print!)

;; defonce means this doesn't get redefined on page reload
(defonce app-state (atom {:text "I see!"}))

(rum/defc hello < rum/reactive []
  (let [text (:text (rum/react app-state))]
    [:div
    (mdl/button {:mdl [:fab :colored]} (mdl/icon "add"))  
    (mdl/button {:mdl [:fab :colored]} (mdl/icon "add"))  
     [:p text]]))

(rum/defc toolbar [& buttons]
  (mdl/grid
    {:mdl   [:color-text--grey-600]}
    (apply mdl/cell {:mdl [:12]} buttons)))

(rum/defc add-toolbar [] 
  (toolbar (mdl/button {:mdl [:fab :colored]} (mdl/icon "add"))
           (mdl/button {:mdl [:fab :colored]} (mdl/icon "add"))))

(rum/mount (hello)
           (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
