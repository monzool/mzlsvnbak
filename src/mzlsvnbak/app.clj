(ns mzlsvnbak.app
  (:require [clojure.tools.cli :refer [parse-opts]]))


(def cli-options
  ;; An option with a required argument
  [["-v" nil "Verbosity level"
    :id :verbosity
    :default 0
    :update-fn inc]
   ;; A boolean option defaulting to nil
   ["-h" "--help"]])


(defn show-help
  ([cmd] (if (some? cmd)
           (println "This is help for:" cmd)
           (println "Got nil")))
  ([] (println "This is help")))


(defn cli-commmand [args]
  (let [cmd (first args)]
    (println "first=" cmd)
    (cond
      (= "add" cmd) (println "What to do:" cmd)
      (#{"cm" "commit"} cmd) (println "What to do:" cmd)
      (#{"mv" "move" "rename"} cmd) (println "What to do:" cmd)
      (#{"rm" "remove" "delete"} cmd) (println "What to do:" cmd)
      (= "backup" cmd) (println "What to do:" cmd)
      (= "help" cmd) (show-help (second args))
      :else (show-help))))



(defn -main [& args]
  (println "Hello")
  (cli-commmand args)
  (comment
    (let [{:keys [options arguments errors]} (parse-opts args cli-options)]
      (println "options" options)
      (println "arguments" arguments)
      (println "errors" errors))))

(comment
  (-main "help")
  ;; (show-help)
  )
