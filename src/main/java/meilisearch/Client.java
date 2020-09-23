/*
 * Unofficial Java client for MeiliSearch
 */
package meilisearch;

import com.google.gson.Gson;

/**
 * Meilisearch client
 */
public class Client {
	public MeilisearchConfig config;
	public IndexesHandler indexesHandler;
	public Gson gson;

	/**
	 * Call instance for MeiliSearch client
	 *
	 * @param config Configuration to connect to Meilisearch instance
	 */
	public Client(MeilisearchConfig config) {
		this.config = config;
		this.gson = new Gson();
		this.indexesHandler = new IndexesHandler(config);
	}

	/**
	 * Create index
	 * Refer https://docs.meilisearch.com/references/indexes.html#create-an-index
	 *
	 * @param uid Unique identifier for the index to create
	 * @return Meilisearch API response
	 * @throws Exception If an error occurs
	 */
	public String createIndex (String uid) throws Exception {
		return this.indexesHandler.create(uid);
	}

	/**
	 * Create index
	 * Refer https://docs.meilisearch.com/references/indexes.html#create-an-index
	 *
	 * @param uid Unique identifier for the index to create
	 * @param primaryKey The primary key of the documents in that index
	 * @return Meilisearch API response
	 * @throws Exception If an error occurs
	 */
	public String createIndex (String uid, String primaryKey) throws Exception {
		return this.indexesHandler.create(uid, primaryKey);
	}

	/**
	 * Get all indexes
	 * Refer https://docs.meilisearch.com/references/indexes.html#list-all-indexes
	 *
	 * @return List of indexes in the Meilisearch client
	 * @throws Exception If an error occurs
	 */
	public MeilisearchIndex[] getIndexList () throws Exception {
		MeilisearchIndex[] meilisearchIndexList = gson.fromJson(this.indexesHandler.getAll(), MeilisearchIndex[].class);
		for (MeilisearchIndex indexes: meilisearchIndexList) {
			indexes.setConfig(this.config);
		}
		return meilisearchIndexList;
	}

	/**
	 * Get single index by uid
	 * Refer https://docs.meilisearch.com/references/indexes.html#get-one-index
	 *
	 * @param uid Unique identifier of the index to get
	 * @return Meilisearch API response
	 * @throws Exception If an error occurs
	 */
	public MeilisearchIndex getIndex (String uid) throws Exception {
		MeilisearchIndex indexes = gson.fromJson(this.indexesHandler.get(uid), MeilisearchIndex.class);
		indexes.setConfig(this.config);
		return indexes;
	}

	/**
	 * Update index by uid
	 * Refer https://docs.meilisearch.com/references/indexes.html#update-an-index
	 *
	 * @param uid Unique identifier of the index to update
	 * @param primaryKey Primary key of the documents in the index
	 * @return Meilisearch API response
	 * @throws Exception If an error occurs
	 */
	public String updateIndex (String uid, String primaryKey) throws Exception {
		return this.indexesHandler.updatePrimaryKey(uid, primaryKey);
	}

	/**
	 * Delete single index by uid
	 * Refer https://docs.meilisearch.com/references/indexes.html#get-one-index
	 *
	 * @param uid Unique identifier of the index to delete
	 * @return Meilisearch API response
	 * @throws Exception If an error occurs
	 */
	public String deleteIndex (String uid) throws Exception {
		return this.indexesHandler.delete(uid);
	}
}
