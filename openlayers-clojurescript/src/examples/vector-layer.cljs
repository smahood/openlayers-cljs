(ns examples.vector-layer
 (:require [ol.source.Vector]
           [ol.source.OSM]
           [ol.layer.Vector]
           [ol.style])
 (:import [ol.style Style Fill Stroke Text]
          [ol.format GeoJSON]
          [ol Map View]
          [ol.layer Tile]))


(def my-style (Style.
               #js {:fill   (Fill.
                             #js {:color "rgba(255,255,255,0.6)"})
                    :stroke (Stroke.
                             #js {:color "#319FD3"
                                  :width 1})
                    :text   (Text.
                             #js {:font   "12px Calibri,sans-serif"
                                  :fill   (Fill.
                                           #js {:color "#000"})
                                  :stroke (Stroke.
                                           #js {:color "#fff"
                                                :width 3})})}))

(def my-vector-layer (ol.layer.Vector.
                      #js {:source (ol.source.Vector.
                                    #js {:url    "https://openlayers.org/en/v4.0.1/examples/data/geojson/countries.geojson"
                                         :format (GeoJSON.)
                                         :style  (fn [feature resolution]
                                                     (ol.style/getText
                                                      (ol.style/setText
                                                       (if (< resolution 5000)
                                                        (:name feature)
                                                        ""))))})}))

(def my-osm-tile (Tile.
                  #js {:source (ol.source.OSM.)}))

(def my-map (Map.
             #js {:target "map"
                  :layers #js [my-osm-tile
                               my-vector-layer]
                  :view   (View. #js {:center #js [0 0]
                                      :zoom   1})}))

(def highlightStyleCache #js {})

(def featureOverlay (ol.layer.Vector.
                     #js {:source (ol.source.Vector.)
                          :map my-map}))







(js/console.log my-style)
(js/console.log my-vector-layer)
(js/console.log my-map)