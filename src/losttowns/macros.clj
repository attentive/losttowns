(ns losttowns.macros
  (:require [cljs.core :as cljs]))

(defmacro adapt-react [rum-name react-component]
  `(rum/defc ~rum-name [props# & children#]
     (.createElement js/React ~react-component (cljs/clj->js props#) (cljs/array children#))))

