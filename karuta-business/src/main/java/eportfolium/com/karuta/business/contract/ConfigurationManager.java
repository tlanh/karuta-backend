/* =======================================================
	Copyright 2020 - ePortfolium - Licensed under the
	Educational Community License, Version 2.0 (the "License"); you may
	not use this file except in compliance with the License. You may
	obtain a copy of the License at

	http://www.osedu.org/licenses/ECL-2.0

	Unless required by applicable law or agreed to in writing,
	software distributed under the License is distributed on an "AS IS"
	BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
	or implied. See the License for the specific language governing
	permissions and limitations under the License.
   ======================================================= */

package eportfolium.com.karuta.business.contract;

import java.util.List;
import java.util.Map;

public interface ConfigurationManager {

	/**
	 * Load all configuration data
	 */
	public void loadConfiguration();

	/**
	 * Get a single configuration value (in one language only)
	 * @param key - Key wanted
	 * @param id_lang - Language ID
	 *
	 * @return string Value
	 */

	String get(String key, Integer id_lang);

	/**
	 * Get a single configuration value (in one language only)
	 *
	 * @param key - Key wanted
	 * @return string Value
	 */
	String get(String key);

	/**
	 * Get several configuration values (in one language only)
	 * @param keys - Keys wanted
	 * @param langID - Language ID
	 *
	 * @return array Values
	 */
	public Map<String, String> getMultiple(List<String> keys, Integer langID);


	/**
	 * Set TEMPORARY a single configuration value (in one language only)
	 * @param key - Key wanted
	 * @param values - values is an array if the configuration is multilingual,
	 *               a single string else.
	 */
	void set(String key, Map<Integer, String> values);

	String getKarutaURL(Boolean ssl);
}
