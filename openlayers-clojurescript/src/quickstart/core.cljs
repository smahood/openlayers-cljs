(ns quickstart.core
  (:require [ol.Map]
            [ol.View]
            [ol.proj]
            [ol.source.OSM]
            [ol.layer.Tile]))

(def my-center (ol.proj/fromLonLat #js [37.41 8.82]))

(def my-view (ol.View. #js{:center my-center
                           :zoom 4}))

(def my-source (ol.source.OSM.))

(def my-tile (ol.layer.Tile. #js {:source my-source}))

(def my-layers #js [my-tile])

(def my-map (ol.Map. #js {:target "map"
                          :layers my-layers
                          :view my-view}))

(.log js/console my-source)
(.log js/console my-tile)
(.log js/console my-layers)
(.log js/console my-center)
(.log js/console my-view)
(.log js/console my-map)
