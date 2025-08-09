# Use OpenJDK 8 as base image
FROM openjdk:8-jre-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file
COPY build/jar/PolygonAreaCalculator.jar /app/

# Create a non-root user
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# Change ownership of the app directory
RUN chown -R appuser:appgroup /app

# Switch to non-root user
USER appuser

# Expose port (if needed for web deployment)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "PolygonAreaCalculator.jar"] 