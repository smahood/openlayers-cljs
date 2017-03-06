(ns examples.vector-layer
 (:require [ol.source.Vector]
           [ol.source.OSM]
           [ol.layer.Vector]
           [ol.style])
 (:import [ol.style Style Fill Stroke Text]
          [ol.format GeoJSON]
          [ol Map View]
          [ol.layer Tile]))

(def current-highlight (atom nil))


(def my-style (Style.
               #js {:fill   (Fill.
                             #js {:color "rgba(255,0,0,0.6)"})
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


(def feature-overlay (ol.layer.Vector.
                      #js {:source (ol.source.Vector.)
                           :map    my-map
                           :style  my-style}))


(defn display-feature-info [pixel]
      (let [feature (.forEachFeatureAtPixel my-map pixel (fn [feat _]
                                                             feat))
            info (.getElementById js/document "info")]
           (if feature
            (set! (.-innerHTML info) (str (.getId feature) ": " (.get feature "name")))
            (set! (.-innerHTML info) (str "")))))


(defn select-feature [pixel]
      (let [feature (.forEachFeatureAtPixel my-map pixel (fn [feat _]
                                                             feat))]
           (if (not (= feature @current-highlight))
            (do
             (if @current-highlight
              (do
               (.removeFeature (.getSource feature-overlay) @current-highlight)
               (reset! current-highlight nil)))
             (if feature
              (do
               (.addFeature (.getSource feature-overlay) feature)
               (reset! current-highlight feature)))))))


(.on my-map "pointermove" (fn [event]
                              (if (not (.-dragging event))
                               (display-feature-info
                                (.getEventPixel my-map
                                                (.-originalEvent event))))))


(.on my-map "click" (fn [event]
                        (select-feature (.-pixel event))))