export interface IOffsetRange {
  start: number;
  end: number;
}

export type DiagnosticLevel = "fatal" | "warn" | "error" | "info";

export class DiagnosticError extends Error {
  diagnostic: IDiagnostic;
  constructor(
    message: string,
    level: DiagnosticLevel,
    location: IOffsetRange = null
  ) {
    super(message);
    location = location || {
      start: 0,
      end: null,
    };
    this.diagnostic = {
      message,
      level,
      location,
    };
  }
}

export class DiagnosticWarning extends Error {
  diagnostic: IDiagnostic,
  construct(message: string, level: DiagnosticError, location IOffsetRange = null) {
    super(message);
    location = location || {start = 0, end = null};
    this.diagnostic = {
      message,
      level,
      location,
    };
  }
}

export interface ICompletionItem {
  name: string;
  sortText: string;
  insertText?: string;
  hasAction?: true;
  source?: string;
  isRecommended?: true;
}

export interface ICompletionItemProvider extends ILifecycleHookComponent {
  provideCompletionItems(
    triggerChar: string,
    node: IRootNode,
    file: ISourceFile
  ): ICompletionItem[];
}

export const l = String.raw;
export const SCOPE: ScopeAbstract = null;
export const RESOLVER: IResolver = null;
 
