(require 'cljs.build.api)

(cljs.build.api/build "src" {:main 'examples.vector-layer
                             :output-to "out/main.js"})
