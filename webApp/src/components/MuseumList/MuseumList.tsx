import './MuseumList.css';

import { useEffect, useState } from 'react';
import type { MuseumObject } from 'shared';
import { museumStore } from '../../museumStore.ts';

interface MuseumListProps {
  onObjectClick: (objectId: number) => void;
}

export function MuseumList({ onObjectClick }: MuseumListProps) {
  const [objects, setObjects] = useState<MuseumObject[] | null>(null);

  useEffect(() => {
    let active = true;
    museumStore.getObjects().then(loaded => {
      if (active) setObjects(loaded);
    });
    return () => {
      active = false;
    };
  }, []);

  if (objects === null) {
    return <div className="museum-message">Loading…</div>;
  }

  if (objects.length === 0) {
    return <div className="museum-message">No data available</div>;
  }

  return (
    <div className="museum-grid">
      {objects.map(obj => (
        <button
          className="museum-card"
          key={obj.objectID}
          onClick={() => onObjectClick(obj.objectID)}
        >
          <img className="museum-card-image" src={obj.primaryImageSmall} alt={obj.title} />
          <span className="museum-card-title">{obj.title}</span>
          <span className="museum-card-artist">{obj.artistDisplayName}</span>
          <span className="museum-card-date">{obj.objectDate}</span>
        </button>
      ))}
    </div>
  );
}
