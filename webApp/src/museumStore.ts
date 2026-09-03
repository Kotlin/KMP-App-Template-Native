import { MuseumStore } from 'shared';

/**
 * The museum data, loaded by the Kotlin Multiplatform `shared` module.
 * One instance for the whole app, so that the data is only fetched once.
 */
export const museumStore = new MuseumStore();
