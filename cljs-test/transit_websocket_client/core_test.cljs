(ns transit-websocket-client.core-test
  (:require [cljs.core.async :refer [<! >!]]
            [transit-websocket-client.core :as ws-lib]
            [cljs.reader :as reader])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(def url "ws://localhost:7890")

(def aws
  (ws-lib/async-websocket "ws://localhost:7890"))

(defn send-msg [msg]
  (.log js/console "Calling Send Message with Value: " (str msg))
  (go (>! aws msg)))

(defn get-msg [store-fn]
  (go
    (store-fn (reader/read-string (<! aws)))))

