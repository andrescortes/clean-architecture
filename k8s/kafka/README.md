# **_K8s, Kafka Kraft, and microservices_**

### Apply resources to Kafka Kraft cluster
```bash
   kubectl apply -f kafka/kafka-stf.yaml
```
Set kafka-app namespace up as current namespace
```bash
   kubectl config set-context --current --namespace=kafka-app
```
```bash
   kubectl apply -f kafka/prod/prod-deployment.yml
```
```bash
   kubectl apply -f kafka/cons/cons-deployment.yml
```
```bash
   kubectl get pods
```
```bash
   kubectl describe pod kafka-0
```
```bash
   kubectl logs app-prod-pod --follow
```

### [Apply resources to ingress](https://minikube.sigs.k8s.io/docs/handbook/addons/ingress-dns/)
```bash
   minikube addons enable ingress
   minikube addons enable ingress-dns
```
```bash
   kubectl apply -f ingress.yaml
```
### Add hosts
```bash
    # Get ip from minikube
    minikube ip
```
```
    # Put ip and host in /etc/host
    190.0.0.1 microservices.local
    190.0.0.1 kafka.local
```
### Clear workspace
```bash
    minikube stop
```
```bash
    minikube delete --all
```
### [Basic Commands](https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#-strong-getting-started-strong-)

#### Cluster Information
```
kubectl cluster-info                # Display cluster information
kubectl version                     # Show client and server version
kubectl get nodes                   # List all nodes in the cluster
kubectl top nodes                   # Show resource usage (CPU/Memory) for nodes
```

#### Namespace Operations
```
kubectl get namespaces              # List all namespaces
kubectl create namespace <name>     # Create a new namespace
kubectl config set-context --current --namespace=<name>  # Set default namespace
```
### Resource Management
#### Common Resources
```
kubectl get pods                    # List all pods in current namespace
kubectl get pods -A                 # List all pods in all namespaces
kubectl get pods -o wide            # List pods with more details
kubectl get deployments             # List deployments
kubectl get services                # List services
kubectl get ingress                 # List ingress resources
kubectl get pv                      # List persistent volumes
kubectl get pvc                     # List persistent volume claims
```
#### Detailed Inspection
```
kubectl describe pod <pod-name>     # Show detailed information about a pod
kubectl describe node <node-name>   # Show detailed node information
kubectl logs <pod-name>             # Show pod logs
kubectl logs -f <pod-name>          # Stream pod logs (follow)
kubectl logs -c <container> <pod>   # Logs from specific container in pod
kubectl exec -it <pod> -- bash      # Start interactive shell in pod
```
#### Creating/Updating Resources
```
kubectl apply -f file.yaml          # Create/update resources from YAML
kubectl create -f file.yaml         # Create resources from YAML
kubectl delete -f file.yaml         # Delete resources defined in YAML
kubectl edit <resource>/<name>      # Edit resource in default editor
```
#### Debugging and Troubleshooting
```
kubectl get events --sort-by=.metadata.creationTimestamp  # Show cluster events
kubectl get events -w               # Watch events in real-time
kubectl get pods --watch -l app=kafka  # Watch pods with specific label
kubectl port-forward <pod> 8080:80  # Forward pod port to local machine
kubectl cp <pod>:<path> <local>     # Copy files from pod to local
kubectl cp <local> <pod>:<path>     # Copy files from local to pod
kubectl run -i --tty busybox --image=busybox --restart=Never -- sh  # Run temporary debug pod
```
#### Scaling and Updates
```
kubectl scale --replicas=3 deployment/<name>  # Scale a deployment
kubectl rollout status deployment/<name>      # Check rollout status
kubectl rollout history deployment/<name>     # View rollout history
kubectl rollout undo deployment/<name>        # Rollback to previous version
kubectl autoscale deployment <name> --min=2 --max=5 --cpu-percent=80  # Auto-scale
```
#### Context and Configuration
```
kubectl config view                # Show current config
kubectl config current-context     # Show current context
kubectl config use-context <name>  # Switch to different context
kubectl config get-contexts        # List all available contexts
```
#### Advanced Commands
```
kubectl api-resources              # List all API resources
kubectl explain <resource>         # Show documentation for a resource
kubectl get <resource> -o yaml     # Get resource in YAML format
kubectl get <resource> -o json     # Get resource in JSON format
kubectl label <resource> <key>=<value>  # Add label to resource
kubectl annotate <resource> <key>=<value>  # Add annotation to resource
kubectl drain <node> --ignore-daemonsets  # Drain node for maintenance
kubectl cordon <node>              # Mark node as unschedulable
kubectl uncordon <node>            # Mark node as schedulable
```
#### Tips
```
    Add -n <namespace> to any command to target a specific namespace

    Add --all-namespaces or -A to target all namespaces

    Use -o wide for additional columns in output

    Use -o yaml or -o json for machine-readable output

    Use --dry-run=client -o yaml to generate YAML without creating resources
```
**_Remember to replace <...> placeholders with your actual resource names._**
