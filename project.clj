(defproject hump "0.1.0-SNAPSHOT"
  :description "Simple deployment tool"
  :url "https://github.com/ysasaki/hump"
  :license {:name "MIT"
            :url "https://github.com/ysasaki/hump/LICENSE"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main hump.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
