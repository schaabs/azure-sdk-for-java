/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.language.luis.authoring;

import com.microsoft.azure.cognitiveservices.language.luis.authoring.models.ListPhraseListsOptionalParameter;
import com.microsoft.azure.cognitiveservices.language.luis.authoring.models.ListFeaturesOptionalParameter;
import com.microsoft.azure.cognitiveservices.language.luis.authoring.models.UpdatePhraseListOptionalParameter;
import com.microsoft.azure.cognitiveservices.language.luis.authoring.models.ErrorResponseException;
import com.microsoft.azure.cognitiveservices.language.luis.authoring.models.FeaturesResponseObject;
import com.microsoft.azure.cognitiveservices.language.luis.authoring.models.OperationStatus;
import com.microsoft.azure.cognitiveservices.language.luis.authoring.models.PhraselistCreateObject;
import com.microsoft.azure.cognitiveservices.language.luis.authoring.models.PhraseListFeatureInfo;
import com.microsoft.azure.cognitiveservices.language.luis.authoring.models.PhraselistUpdateObject;
import java.util.List;
import java.util.UUID;
import rx.Observable;

/**
 * An instance of this class provides access to all the operations defined
 * in Features.
 */
public interface Features {

    /**
     * Creates a new phraselist feature.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param phraselistCreateObject A Phraselist object containing Name, comma-separated Phrases and the isExchangeable boolean.
      *  Default value for isExchangeable is true.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the int object if successful.
     */
    int addPhraseList(UUID appId, String versionId, PhraselistCreateObject phraselistCreateObject);

    /**
     * Creates a new phraselist feature.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param phraselistCreateObject A Phraselist object containing Name, comma-separated Phrases and the isExchangeable boolean.
      *  Default value for isExchangeable is true.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the int object
     */
    Observable<Integer> addPhraseListAsync(UUID appId, String versionId, PhraselistCreateObject phraselistCreateObject);


    /**
     * Gets all the phraselist features.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param listPhraseListsOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the List&lt;PhraseListFeatureInfo&gt; object if successful.
     */
    
    List<PhraseListFeatureInfo> listPhraseLists(UUID appId, String versionId, ListPhraseListsOptionalParameter listPhraseListsOptionalParameter);

    /**
     * Gets all the phraselist features.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param listPhraseListsOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the List&lt;PhraseListFeatureInfo&gt; object
     */
    
    Observable<List<PhraseListFeatureInfo>> listPhraseListsAsync(UUID appId, String versionId, ListPhraseListsOptionalParameter listPhraseListsOptionalParameter);

    /**
     * Gets all the phraselist features.
     *
     * @return the first stage of the listPhraseLists call
     */
    FeaturesListPhraseListsDefinitionStages.WithAppId listPhraseLists();

    /**
     * Grouping of listPhraseLists definition stages.
     */
    interface FeaturesListPhraseListsDefinitionStages {
        /**
         * The stage of the definition to be specify appId.
         */
        interface WithAppId {
            /**
             * The application ID.
             *
             * @return next definition stage
             */
            WithVersionId withAppId(UUID appId);
        }
        /**
         * The stage of the definition to be specify versionId.
         */
        interface WithVersionId {
            /**
             * The version ID.
             *
             * @return next definition stage
             */
            FeaturesListPhraseListsDefinitionStages.WithExecute withVersionId(String versionId);
        }

        /**
         * The stage of the definition which allows for any other optional settings to be specified.
         */
        interface WithAllOptions {
            /**
             * The number of entries to skip. Default value is 0.
             *
             * @return next definition stage
             */
            FeaturesListPhraseListsDefinitionStages.WithExecute withSkip(Integer skip);

            /**
             * The number of entries to return. Maximum page size is 500. Default is 100.
             *
             * @return next definition stage
             */
            FeaturesListPhraseListsDefinitionStages.WithExecute withTake(Integer take);

        }

        /**
         * The last stage of the definition which will make the operation call.
        */
        interface WithExecute extends FeaturesListPhraseListsDefinitionStages.WithAllOptions {
            /**
             * Execute the request.
             *
             * @return the List&lt;PhraseListFeatureInfo&gt; object if successful.
             */
            List<PhraseListFeatureInfo> execute();

            /**
             * Execute the request asynchronously.
             *
             * @return the observable to the List&lt;PhraseListFeatureInfo&gt; object
             */
            Observable<List<PhraseListFeatureInfo>> executeAsync();
        }
    }

    /**
     * The entirety of listPhraseLists definition.
     */
    interface FeaturesListPhraseListsDefinition extends
        FeaturesListPhraseListsDefinitionStages.WithAppId,
        FeaturesListPhraseListsDefinitionStages.WithVersionId,
        FeaturesListPhraseListsDefinitionStages.WithExecute {
    }

    /**
     * Gets all the extraction features for the specified application version.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param listOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the FeaturesResponseObject object if successful.
     */
    
    FeaturesResponseObject list(UUID appId, String versionId, ListFeaturesOptionalParameter listOptionalParameter);

    /**
     * Gets all the extraction features for the specified application version.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param listOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the FeaturesResponseObject object
     */
    
    Observable<FeaturesResponseObject> listAsync(UUID appId, String versionId, ListFeaturesOptionalParameter listOptionalParameter);

    /**
     * Gets all the extraction features for the specified application version.
     *
     * @return the first stage of the list call
     */
    FeaturesListDefinitionStages.WithAppId list();

    /**
     * Grouping of list definition stages.
     */
    interface FeaturesListDefinitionStages {
        /**
         * The stage of the definition to be specify appId.
         */
        interface WithAppId {
            /**
             * The application ID.
             *
             * @return next definition stage
             */
            WithVersionId withAppId(UUID appId);
        }
        /**
         * The stage of the definition to be specify versionId.
         */
        interface WithVersionId {
            /**
             * The version ID.
             *
             * @return next definition stage
             */
            FeaturesListDefinitionStages.WithExecute withVersionId(String versionId);
        }

        /**
         * The stage of the definition which allows for any other optional settings to be specified.
         */
        interface WithAllOptions {
            /**
             * The number of entries to skip. Default value is 0.
             *
             * @return next definition stage
             */
            FeaturesListDefinitionStages.WithExecute withSkip(Integer skip);

            /**
             * The number of entries to return. Maximum page size is 500. Default is 100.
             *
             * @return next definition stage
             */
            FeaturesListDefinitionStages.WithExecute withTake(Integer take);

        }

        /**
         * The last stage of the definition which will make the operation call.
        */
        interface WithExecute extends FeaturesListDefinitionStages.WithAllOptions {
            /**
             * Execute the request.
             *
             * @return the FeaturesResponseObject object if successful.
             */
            FeaturesResponseObject execute();

            /**
             * Execute the request asynchronously.
             *
             * @return the observable to the FeaturesResponseObject object
             */
            Observable<FeaturesResponseObject> executeAsync();
        }
    }

    /**
     * The entirety of list definition.
     */
    interface FeaturesListDefinition extends
        FeaturesListDefinitionStages.WithAppId,
        FeaturesListDefinitionStages.WithVersionId,
        FeaturesListDefinitionStages.WithExecute {
    }


    /**
     * Gets phraselist feature info.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param phraselistId The ID of the feature to be retrieved.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the PhraseListFeatureInfo object if successful.
     */
    PhraseListFeatureInfo getPhraseList(UUID appId, String versionId, int phraselistId);

    /**
     * Gets phraselist feature info.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param phraselistId The ID of the feature to be retrieved.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the PhraseListFeatureInfo object
     */
    Observable<PhraseListFeatureInfo> getPhraseListAsync(UUID appId, String versionId, int phraselistId);


    /**
     * Updates the phrases, the state and the name of the phraselist feature.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param phraselistId The ID of the feature to be updated.
     * @param updatePhraseListOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the OperationStatus object if successful.
     */
    
    OperationStatus updatePhraseList(UUID appId, String versionId, int phraselistId, UpdatePhraseListOptionalParameter updatePhraseListOptionalParameter);

    /**
     * Updates the phrases, the state and the name of the phraselist feature.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param phraselistId The ID of the feature to be updated.
     * @param updatePhraseListOptionalParameter the object representing the optional parameters to be set before calling this API
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the OperationStatus object
     */
    
    Observable<OperationStatus> updatePhraseListAsync(UUID appId, String versionId, int phraselistId, UpdatePhraseListOptionalParameter updatePhraseListOptionalParameter);

    /**
     * Updates the phrases, the state and the name of the phraselist feature.
     *
     * @return the first stage of the updatePhraseList call
     */
    FeaturesUpdatePhraseListDefinitionStages.WithAppId updatePhraseList();

    /**
     * Grouping of updatePhraseList definition stages.
     */
    interface FeaturesUpdatePhraseListDefinitionStages {
        /**
         * The stage of the definition to be specify appId.
         */
        interface WithAppId {
            /**
             * The application ID.
             *
             * @return next definition stage
             */
            WithVersionId withAppId(UUID appId);
        }
        /**
         * The stage of the definition to be specify versionId.
         */
        interface WithVersionId {
            /**
             * The version ID.
             *
             * @return next definition stage
             */
            WithPhraselistId withVersionId(String versionId);
        }
        /**
         * The stage of the definition to be specify phraselistId.
         */
        interface WithPhraselistId {
            /**
             * The ID of the feature to be updated.
             *
             * @return next definition stage
             */
            FeaturesUpdatePhraseListDefinitionStages.WithExecute withPhraselistId(int phraselistId);
        }

        /**
         * The stage of the definition which allows for any other optional settings to be specified.
         */
        interface WithAllOptions {
            /**
             * The new values for: - Just a boolean called IsActive, in which case the status of the feature will be
             *   changed. - Name, Pattern, Mode, and a boolean called IsActive to update the feature.
             *
             * @return next definition stage
             */
            FeaturesUpdatePhraseListDefinitionStages.WithExecute withPhraselistUpdateObject(PhraselistUpdateObject phraselistUpdateObject);

        }

        /**
         * The last stage of the definition which will make the operation call.
        */
        interface WithExecute extends FeaturesUpdatePhraseListDefinitionStages.WithAllOptions {
            /**
             * Execute the request.
             *
             * @return the OperationStatus object if successful.
             */
            OperationStatus execute();

            /**
             * Execute the request asynchronously.
             *
             * @return the observable to the OperationStatus object
             */
            Observable<OperationStatus> executeAsync();
        }
    }

    /**
     * The entirety of updatePhraseList definition.
     */
    interface FeaturesUpdatePhraseListDefinition extends
        FeaturesUpdatePhraseListDefinitionStages.WithAppId,
        FeaturesUpdatePhraseListDefinitionStages.WithVersionId,
        FeaturesUpdatePhraseListDefinitionStages.WithPhraselistId,
        FeaturesUpdatePhraseListDefinitionStages.WithExecute {
    }


    /**
     * Deletes a phraselist feature.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param phraselistId The ID of the feature to be deleted.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @throws ErrorResponseException thrown if the request is rejected by server
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent
     * @return the OperationStatus object if successful.
     */
    OperationStatus deletePhraseList(UUID appId, String versionId, int phraselistId);

    /**
     * Deletes a phraselist feature.
     *
     * @param appId The application ID.
     * @param versionId The version ID.
     * @param phraselistId The ID of the feature to be deleted.
     * @throws IllegalArgumentException thrown if parameters fail the validation
     * @return the observable to the OperationStatus object
     */
    Observable<OperationStatus> deletePhraseListAsync(UUID appId, String versionId, int phraselistId);


}
