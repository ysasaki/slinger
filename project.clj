(defproject slinger "0.1.0-SNAPSHOT"
  :description "Simple deployment tool"
  :url "https://github.com/ysasaki/slinger"
  :license {:name "MIT"
            :url "https://github.com/ysasaki/slinger/LICENSE"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main slinger.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
