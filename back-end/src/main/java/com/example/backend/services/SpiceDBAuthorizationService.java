package com.example.backend.services;

import com.authzed.api.v1.CheckPermissionRequest;
import com.authzed.api.v1.CheckPermissionResponse;
import com.authzed.api.v1.Consistency;
import com.authzed.api.v1.ObjectReference;
import com.authzed.api.v1.PermissionsServiceGrpc;
import com.authzed.api.v1.Relationship;
import com.authzed.api.v1.RelationshipUpdate;
import com.authzed.api.v1.SubjectReference;
import com.authzed.api.v1.WriteRelationshipsRequest;
import org.springframework.stereotype.Service;

@Service
public class SpiceDBAuthorizationService {

    private final PermissionsServiceGrpc.PermissionsServiceBlockingStub permissionsService;

    public SpiceDBAuthorizationService(PermissionsServiceGrpc.PermissionsServiceBlockingStub permissionsService) {
        this.permissionsService = permissionsService;
    }

    /**
     * Checks if a subject has a specific permission on an object.
     *
     * @param subject   The type of the subject (e.g., "user", "group")
     * @param subjectId The ID of the subject
     * @param permission The permission to check (e.g., "read", "write", "admin")
     * @param object    The type of the object (e.g., "document", "classroom")
     * @param objectId  The ID of the object
     * @return true if the subject has the permission, false otherwise
     */
    public boolean checkPermission(String subject, String subjectId, String permission, String object, String objectId) {
        try {
            SubjectReference subjectRef = SubjectReference.newBuilder()
                    .setObject(ObjectReference.newBuilder()
                            .setObjectType(subject)
                            .setObjectId(subjectId)
                            .build())
                    .build();

            ObjectReference resourceRef = ObjectReference.newBuilder()
                    .setObjectType(object)
                    .setObjectId(objectId)
                    .build();

            CheckPermissionRequest request = CheckPermissionRequest.newBuilder()
                    .setResource(resourceRef)
                    .setPermission(permission)
                    .setSubject(subjectRef)
                    .setConsistency(Consistency.newBuilder()
                            .setFullyConsistent(true)
                            .build())
                    .build();

            CheckPermissionResponse response = permissionsService.checkPermission(request);

            return response.getPermissionship() == CheckPermissionResponse.Permissionship.PERMISSIONSHIP_HAS_PERMISSION;
        } catch (Exception e) {
            System.err.println("Error checking permission: " + e.getMessage());
            return false;
        }
    }

    /**
     * Creates a relationship between a subject and an object.
     *
     * @param subject   The type of the subject (e.g., "user", "group")
     * @param subjectId The ID of the subject
     * @param relation  The relation to create (e.g., "member", "owner", "viewer")
     * @param object    The type of the object (e.g., "document", "classroom")
     * @param objectId  The ID of the object
     * @return true if the relationship was created successfully, false otherwise
     */
    public boolean makeRelationship(String subject, String subjectId, String relation, String object, String objectId) {
        try {
            SubjectReference subjectRef = SubjectReference.newBuilder()
                    .setObject(ObjectReference.newBuilder()
                            .setObjectType(subject)
                            .setObjectId(subjectId)
                            .build())
                    .build();

            ObjectReference resourceRef = ObjectReference.newBuilder()
                    .setObjectType(object)
                    .setObjectId(objectId)
                    .build();

            Relationship relationship = Relationship.newBuilder()
                    .setResource(resourceRef)
                    .setRelation(relation)
                    .setSubject(subjectRef)
                    .build();

            RelationshipUpdate update = RelationshipUpdate.newBuilder()
                    .setOperation(RelationshipUpdate.Operation.OPERATION_TOUCH)
                    .setRelationship(relationship)
                    .build();

            WriteRelationshipsRequest request = WriteRelationshipsRequest.newBuilder()
                    .addUpdates(update)
                    .build();

            permissionsService.writeRelationships(request);
            return true;
        } catch (Exception e) {
            System.err.println("Error creating relationship: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a relationship between a subject and an object.
     *
     * @param subject   The type of the subject (e.g., "user", "group")
     * @param subjectId The ID of the subject
     * @param relation  The relation to delete
     * @param object    The type of the object
     * @param objectId  The ID of the object
     * @return true if the relationship was deleted successfully, false otherwise
     */
    public boolean deleteRelationship(String subject, String subjectId, String relation, String object, String objectId) {
        try {
            SubjectReference subjectRef = SubjectReference.newBuilder()
                    .setObject(ObjectReference.newBuilder()
                            .setObjectType(subject)
                            .setObjectId(subjectId)
                            .build())
                    .build();

            ObjectReference resourceRef = ObjectReference.newBuilder()
                    .setObjectType(object)
                    .setObjectId(objectId)
                    .build();

            Relationship relationship = Relationship.newBuilder()
                    .setResource(resourceRef)
                    .setRelation(relation)
                    .setSubject(subjectRef)
                    .build();

            RelationshipUpdate update = RelationshipUpdate.newBuilder()
                    .setOperation(RelationshipUpdate.Operation.OPERATION_DELETE)
                    .setRelationship(relationship)
                    .build();

            WriteRelationshipsRequest request = WriteRelationshipsRequest.newBuilder()
                    .addUpdates(update)
                    .build();

            permissionsService.writeRelationships(request);
            return true;
        } catch (Exception e) {
            System.err.println("Error deleting relationship: " + e.getMessage());
            return false;
        }
    }
}
