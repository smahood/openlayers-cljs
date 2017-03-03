(require 'cljs.build.api)

(cljs.build.api/build "src" {:main 'quickstart.core
                             :output-to "out/main.js"})
