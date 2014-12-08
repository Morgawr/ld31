(defproject game "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2371"]
                 ;[local/novelette "0.1.0-indev"]
                 [novelette/novelette "0.1.0-indev"]]
  :plugins [[lein-cljsbuild "1.0.3"]]
  :hooks [leiningen.cljsbuild]
  :repositories { "project" "file:repo" }
  :cljsbuild {
              :builds [{
                        :source-paths ["src/cljs"]
                        :compiler {
                                   :output-to "runtime/js/game.js"
                                   :optimizations :simple
                                   :pretty-print true}
                        :jar true}]})
