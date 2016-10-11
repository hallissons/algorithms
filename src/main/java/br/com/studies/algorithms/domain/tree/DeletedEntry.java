package br.com.studies.algorithms.domain.tree;

import br.com.studies.algorithms.HashEntry;

public class DeletedEntry extends HashEntry {
    private static DeletedEntry entry = null;

    private DeletedEntry() {
          super(-1, -1);
    }

    public static DeletedEntry getUniqueDeletedEntry() {
          if (entry == null)
                entry = new DeletedEntry();
          return entry;
    }
}
