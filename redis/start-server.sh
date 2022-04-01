
for d in ./*/ ; do (cd "$d" && redis-server ./redis.conf &); done


redis-cli --cluster create 192.168.21.128:8000 192.168.21.128:8001 \
192.168.21.128:8002 192.168.21.128:8003 192.168.21.128:8004 192.168.21.128:8005 \
--cluster-replicas 1