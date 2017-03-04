(ns quickstart.core
  (:require [ol.proj])
  (:import [ol.source OSM Vector VectorTile]
           [ol.layer Tile]
           [ol View Map]))

(def my-center (ol.proj/fromLonLat #js [37.41 8.82]))

(def my-view (View. #js{:center my-center
                           :zoom 4}))

(def osm-source (OSM.))

(def osm-tile (Tile. #js {:source osm-source}))

(def vector-source (ol.source.Vector.))



(def my-layers #js [osm-tile])

(def my-map (Map. #js {:target "map"
                       :layers my-layers
                       :view my-view}))

;(.log js/console my-source)
;(.log js/console my-tile)
;(.log js/console my-layers)
;(.log js/console my-center)
;(.log js/console my-view)
;(.log js/console my-map)
