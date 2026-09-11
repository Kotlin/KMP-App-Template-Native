import './MuseumDetail.css';

import { useEffect, useState } from 'react';
import type { MuseumObject } from 'shared';
import { museumStore } from '../../museumStore.ts';

interface MuseumDetailProps {
  objectId: number;
  onBackClick: () => void;
}

export function MuseumDetail({ objectId, onBackClick }: MuseumDetailProps) {
  const [obj, setObj] = useState<MuseumObject | null>(null);

  useEffect(() => {
    let active = true;
    museumStore.getObjectById(objectId).then(loaded => {
      if (active) setObj(loaded ?? null);
    });
    return () => {
      active = false;
    };
  }, [objectId]);

  if (obj === null) {
    return (
      <div className="museum-detail">
        <BackButton onClick={onBackClick} />
        <div className="museum-message">No data available</div>
      </div>
    );
  }

  return (
    <div className="museum-detail">
      <BackButton onClick={onBackClick} />
      <img className="museum-detail-image" src={obj.primaryImageSmall} alt={obj.title} />
      <h1 className="museum-detail-title">{obj.title}</h1>
      <LabeledInfo label="Artist" value={obj.artistDisplayName} />
      <LabeledInfo label="Date" value={obj.objectDate} />
      <LabeledInfo label="Dimensions" value={obj.dimensions} />
      <LabeledInfo label="Medium" value={obj.medium} />
      <LabeledInfo label="Department" value={obj.department} />
      <LabeledInfo label="Repository" value={obj.repository} />
      <LabeledInfo label="Credits" value={obj.creditLine} />
    </div>
  );
}

function BackButton({ onClick }: { onClick: () => void }) {
  return (
    <button className="museum-back" onClick={onClick} aria-label="Back">
      ←
    </button>
  );
}

function LabeledInfo({ label, value }: { label: string; value: string }) {
  return (
    <p className="museum-detail-info">
      <strong>{label}: </strong>
      {value}
    </p>
  );
}
